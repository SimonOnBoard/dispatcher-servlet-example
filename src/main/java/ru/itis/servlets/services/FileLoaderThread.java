package ru.itis.servlets.services;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileLoaderThread extends Thread {
    private volatile String[] arguments;
    private HttpServletResponse httpServletResponse;
    public FileLoaderThread(String[] arguments, HttpServletResponse httpServletResponse) {
        this.arguments = arguments;
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try (InputStream inputStream = new FileInputStream(new File(arguments[0]));) {
            httpServletResponse.setContentType(arguments[1]);
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\" " + arguments[2] + "\"");
            IOUtils.copy(inputStream, httpServletResponse.getOutputStream());
            httpServletResponse.getOutputStream().flush();
            httpServletResponse.getOutputStream().close();
        } catch (IOException e) {
            throw new IllegalStateException("Something went wrong while delivering file");
        }
    }
}
