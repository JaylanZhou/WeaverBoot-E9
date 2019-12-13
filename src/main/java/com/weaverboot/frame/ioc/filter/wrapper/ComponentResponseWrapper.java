package com.weaverboot.frame.ioc.filter.wrapper;

import weaver.general.BaseBean;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class ComponentResponseWrapper extends HttpServletResponseWrapper {

    private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

    private HttpServletResponse response;

    private PrintWriter pwrite;

    public ComponentResponseWrapper(HttpServletResponse response) {

        super(response);

        this.response = response;

    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {

        return new MyServletOutputStream(bytes); // 将数据写到 byte 中

    }

    /**
     * 重写父类的 getWriter() 方法，将响应数据缓存在 PrintWriter 中
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        try{
            pwrite = new PrintWriter(new OutputStreamWriter(bytes, "UTF-8"));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pwrite;
    }

    /**
     * 获取缓存在 PrintWriter 中的响应数据
     * @return
     */
    public byte[] getBytes() {

        if(null != pwrite) {

            pwrite.close();

            return bytes.toByteArray();

        }

        if(null != bytes) {
            try {
                bytes.flush();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        BaseBean baseBean = new BaseBean();

        return bytes.toByteArray();
    }

    class MyServletOutputStream extends ServletOutputStream {
        private ByteArrayOutputStream ostream ;

        public MyServletOutputStream(ByteArrayOutputStream ostream) {
            this.ostream = ostream;
        }

        @Override
        public void write(int b) throws IOException {
            ostream.write(b); // 将数据写到 stream　中
        }

    }

}
