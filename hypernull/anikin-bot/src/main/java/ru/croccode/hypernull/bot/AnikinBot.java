package ru.croccode.hypernull.bot;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import ru.croccode.hypernull.bot.modules.Collector;
import ru.croccode.hypernull.bot.modules.Deathmatcher;
import ru.croccode.hypernull.domain.MatchMode;
import ru.croccode.hypernull.geometry.Offset;
import ru.croccode.hypernull.geometry.Point;
import ru.croccode.hypernull.geometry.Size;
import ru.croccode.hypernull.io.SocketSession;
import ru.croccode.hypernull.message.Hello;
import ru.croccode.hypernull.message.MatchOver;
import ru.croccode.hypernull.message.MatchStarted;
import ru.croccode.hypernull.message.Move;
import ru.croccode.hypernull.message.Register;
import ru.croccode.hypernull.message.Update;

public class AnikinBot implements Bot {

	private static final Random rnd = new Random(System.currentTimeMillis());

	private final MatchMode mode;
	private int myId;
	private int myViewRadius;
	private Offset moveOffset;
	private int moveCounter = 0;
	private static Deathmatcher deathmatcher;
	private static Collector collector;


	public AnikinBot(MatchMode mode) {
		this.mode = mode;
	}

	@Override
	public Register onHello(Hello hello) {
		Register register = new Register();
		register.setMode(mode);
		register.setBotName("Anikin-bot");
		return register;
	}

	@Override
	public void onMatchStarted(MatchStarted matchStarted) {
		moveHistory = new int[matchStarted.getMapSize().width()]
				[matchStarted.getMapSize().height()];
		myId = matchStarted.getYourId();
		myViewRadius = matchStarted.getViewRadius();
		coinSpawns = new ArrayList<>();
		size = new Size(matchStarted.getMapSize().width(), matchStarted.getMapSize().height());
	}

	@Override
	public void onMatchOver(MatchOver matchOver) {
	}

	// moveHistory хранит историю посещенных локаций:
	// 0 - неизвестная, 1 - увиденная, 2 - посещенная, 3 - препятствие, 4 - монетка
	private static int[][] moveHistory;
	private static ArrayList<Point> coinSpawns;
	Point prevPosition;
	private static int count = 0;
	private static Size size;

	// Обновления карты бота
	private static void refreshMap(Update update, Point tempPoint) {
		moveHistory[tempPoint.x()][tempPoint.y()] = 2;
		count++;
		// Добавляем на карту препятствия
		for (Point block : update.getBlocks()) {
			moveHistory[block.x()][block.y()] = 3;
		}
		// Добавляем на карту монетки, также запоминаем их места генерации
		for (Point coin : update.getCoins()) {
			moveHistory[coin.x()][coin.y()] = 4;
			coinSpawns.add(coin);
		}
	}


	public static Offset getRandomOffset(Point tempPoint) {
		// проверяем все точки вокруг и выбираем свободную, в которой еще не были
		// думаю, куча условий надежнее бесконечного цикла рандомной генерации с постоянными проверками
		if (moveHistory[tempPoint.x() + 1][tempPoint.y() + 1] != 3 &&
				moveHistory[tempPoint.x() + 1][tempPoint.y() + 1] != 2) {
			return new Offset(1, 1);
		}
		if (moveHistory[tempPoint.x() + 1][tempPoint.y()] != 3 &&
				moveHistory[tempPoint.x() + 1][tempPoint.y()] != 2) {
			return new Offset(1, 0);
		}
		if (moveHistory[tempPoint.x() + 1][tempPoint.y() - 1] != 3 &&
				moveHistory[tempPoint.x() + 1][tempPoint.y() - 1] != 2) {
			return new Offset(1, -1);
		}
		if (moveHistory[tempPoint.x()][tempPoint.y() - 1] != 3 &&
				moveHistory[tempPoint.x()][tempPoint.y() - 1] != 2) {
			return new Offset(0, -1);
		}
		if (moveHistory[tempPoint.x() - 1][tempPoint.y() - 1] != 3 &&
				moveHistory[tempPoint.x() - 1][tempPoint.y() - 1] != 2) {
			return new Offset(-1, -1);
		}
		if (moveHistory[tempPoint.x() - 1][tempPoint.y()] != 3 &&
				moveHistory[tempPoint.x() - 1][tempPoint.y()] != 2) {
			return new Offset(-1, 0);
		}
		if (moveHistory[tempPoint.x() - 1][tempPoint.y() + 1] != 3 &&
				moveHistory[tempPoint.x() - 1][tempPoint.y() + 1] != 2) {
			return new Offset(-1, 1);
		}
		if (moveHistory[tempPoint.x()][tempPoint.y() + 1] != 3 &&
				moveHistory[tempPoint.x()][tempPoint.y() + 1] != 2) {
			return new Offset(0, 1);
		}
		// если обошли все точки вокруг, идем в любую свободную
		if (moveHistory[tempPoint.x() + 1][tempPoint.y() + 1] != 3) {
			return new Offset(1, 1);
		}
		if (moveHistory[tempPoint.x() + 1][tempPoint.y()] != 3) {
			return new Offset(1, 0);
		}
		if (moveHistory[tempPoint.x() + 1][tempPoint.y() - 1] != 3) {
			return new Offset(1, -1);
		}
		if (moveHistory[tempPoint.x()][tempPoint.y() - 1] != 3) {
			return new Offset(0, -1);
		}
		if (moveHistory[tempPoint.x() - 1][tempPoint.y() - 1] != 3) {
			return new Offset(-1, -1);
		}
		if (moveHistory[tempPoint.x() - 1][tempPoint.y()] != 3) {
			return new Offset(-1, 0);
		}
		if (moveHistory[tempPoint.x() - 1][tempPoint.y() + 1] != 3) {
			return new Offset(-1, 1);
		}
		if (moveHistory[tempPoint.x()][tempPoint.y() + 1] != 3) {
			return new Offset(0, 1);
		}
		// к этому моменту бот должен вернуть значение, но на всякий случай генерирую случайное перемещение
		return new Offset(rnd.nextInt(3) - 1, rnd.nextInt(3) - 1);
	}

	@Override
	public Move onUpdate(Update update) {
		// Обновляем историю посещения
		Point tempPoint = new Point(update.getBots().get(myId).x(), update.getBots().get(myId).y());
		refreshMap(update, tempPoint);
		// т.к. убив другого можно получить все монеты, добытые жертвой,
		// задача первого приоритета для бота - охота на других или спасение самого себя
		// если в поле есть боты, запускаем режим бойца-бегуна
		if (update.getBots().size() > 1) {
			deathmatcher = new Deathmatcher(update, myId);
			moveOffset = deathmatcher.getOffset(moveHistory);
			Move move = new Move();
			move.setOffset(moveOffset);
			return move;
		} else
			// второй приоритет для бота - сбор монеток
			// есть  монеты, но нет враждебных ботов, запускаем режим сборщика монет
			if (!update.getCoins().isEmpty() && (update.getBots().size() == 1)) {
				collector = new Collector(update, myId, size);
				moveOffset = collector.getOffset(moveHistory,coinSpawns);
				Move move = new Move();
				move.setOffset(moveOffset);
				return move;
			} else {
				// если в поле зрения нет монеток или ботов, запускаем режим исследователя
				Move move = new Move();
				move.setOffset(getRandomOffset(tempPoint));
				return move;
			}
	}

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket();
		socket.setTcpNoDelay(true);
		socket.setSoTimeout(300_000);
		socket.connect(new InetSocketAddress("localhost", 2021));

		SocketSession session = new SocketSession(socket);
		AnikinBot bot = new AnikinBot(MatchMode.FRIENDLY);
		new BotMatchRunner(bot, session).run();
	}
}
