package com.johnhiott.stehtosample;

import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;

import java.io.PrintStream;

public class MyPlugin implements DumperPlugin {
    @Override
    public String getName() {
        return "john-plugin";
    }

    @Override
    public void dump(DumperContext dumpContext) throws DumpException {

        PrintStream out = dumpContext.getStdout();
        out.println("John Hiott !@!@!@!@!@!");

    }
}
