package skywolf46.commandannotation.data.command;

import skywolf46.commandannotation.util.ParameterStorage;

public class CommandArgument {
    private String cmd;
    private String[] args;
    private ParameterStorage storage;
    private int pointer = 0;
    private long key;

    public CommandArgument(ParameterStorage storage, String command, String[] args, long key) {
        this.storage = storage;
        this.args = args;
        this.key = key;
        this.cmd = command;
    }

    public ParameterStorage getStorage() {
        return storage;
    }

    public void nextPointer() {
        pointer++;
    }

    public int length() {
        return args.length - pointer;
    }


    public String getCommand() {
        return cmd;
    }

    public String getOriginal(int start) {
        if (start < 0)
            throw new IndexOutOfBoundsException("Argument index < 0");
        int len = args.length;
        if (len <= start) {
            throw new IndexOutOfBoundsException("Argument overflow");
        }
        return args[start];
    }


    public String getOriginalOrNull(int start) {
        if (start < 0)
            return null;
        int len = args.length;
        if (len <= start) {
            return null;
        }
        return args[start];
    }


    public String getOriginal(int start, int end) {
        if (start < 0)
            throw new IndexOutOfBoundsException("Argument index < 0");
        int len = args.length;
        if (len < end) {
            throw new IndexOutOfBoundsException("Argument overflow");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(args[start + i]);
        }
        return sb.toString();
    }

    public String get(int start) {
        if (start < 0)
            throw new IndexOutOfBoundsException("Argument index < 0");
        int len = length();
        if (len <= start) {
            throw new IndexOutOfBoundsException("Argument overflow");
        }
        return args[start + pointer];
    }

    public String getOrNull(int start) {
        if (start < 0)
            return null;
        int len = length();
        if (len <= start) {
            return null;
        }
        return args[start + pointer];
    }

    public String get(int start, int end) {
        if (start < 0)
            throw new IndexOutOfBoundsException("Argument index < 0");
        int len = length();
        if (len < end) {
            throw new IndexOutOfBoundsException("Argument overflow");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(args[start + pointer + i]);
        }
        return sb.toString();
    }
}
