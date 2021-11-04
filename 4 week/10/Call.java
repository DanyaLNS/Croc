public class Call {
    private long start;
    private long end;

    public Call(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getEnd() { return end; }
    public long getStart() {
        return start;
    }
}
