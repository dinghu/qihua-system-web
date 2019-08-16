package com.rqh.system.utils.QrCode;

import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeiXinQRManager {

    private String appid;
    private String secret;

    public WeiXinQRManager(String appid, String secret){
        this.appid=appid;
        this.secret=secret;
    }

    /*
      * get请求获取toker值
      * */
    public static String sendGet(String url, String param){
        String result = "";
        BufferedReader in = null;
        String urlNameString = url;
        try {
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            System.out.println(in);

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param url
     *            基础的url地址
     * @param json
     *            查询参数
     * @return 构建好的url
     */

    public static String httpPostWithJSON(HttpServletRequest request, String url, String json, String id)
            throws Exception {
        String result = null;
        // 将JSON进行UTF-8编码,以便传输中文
        String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        StringEntity se = new StringEntity(json);
        se.setContentType("application/json");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "UTF-8"));
        httpPost.setEntity(se);
        // httpClient.execute(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                InputStream instreams = resEntity.getContent();
                // ResourceBundle systemConfig =
                // ResourceBundle.getBundle("config/system",
                // Locale.getDefault());
                // String uploadSysUrl =
                // systemConfig.getString("agentImgUrl")+id+"/";
                // File saveFile = new File(uploadSysUrl+id+".jpg");
                String uploadSysUrl = "D:\\upload" + "/";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = sdf.format(new Date());
                String xdPath = "/upload/" + dateStr;
                String filePath = request.getServletContext().getRealPath(xdPath);// 保存文件路径
                File saveFile = new File(filePath);
                // 判断这个文件（saveFile）是否存在
                if (!saveFile.exists()) {
                    // 如果不存在就创建这个文件夹
                    saveFile.mkdirs();
                }
                String newHeadImgName = "";// 重新设置要保存头像的文件名
                // 获取当前时间
                Date d = new Date();
                newHeadImgName += "" + d.getTime() + ((int) (Math.random() * 9000 + 1000)) + ".jpg";
                saveToImgByInputStream(instreams, filePath, newHeadImgName);
                result = xdPath + "/" + newHeadImgName;
            }
        }
        return result;
    }

    /*
     * @param instreams 二进制流
     *
     * @param imgPath 图片的保存路径
     *
     * @param imgName 图片的名称
     *
     * @return 1：保存正常 0：保存失败
     */
    public static int saveToImgByInputStream(InputStream instreams, String imgPath, String imgName)
            throws FileNotFoundException {

        int stateInt = 1;
        File file = new File(imgPath, imgName);// 可以是任何图片格式.jpg,.png等
        FileOutputStream fos = new FileOutputStream(file);
        if (instreams != null) {
            try {

                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }

            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {

                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stateInt;
    }

    public static boolean exists(String imgPath) {
        File saveFile = new File(imgPath);
        if (!saveFile.getParentFile().exists()) {
            return false;
        } else {
            // 如果存在判断这个文件的大小
            if (saveFile.length() > 0) {
                System.out.println("--------------------------------" + saveFile.length());
                return true;
            } else {
                return false;
            }
        }

    }

//    /**
//     * 向指定URL发送GET方法的请求
//     *
//     * @param url
//     *            发送请求的URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return URL 所代表远程资源的响应结果
//     */
//    public static String sendGet(String url, String param) {
//        String result = "";
//        BufferedReader in = null;
//        try {
//            String urlNameString = url + "?" + param;
//            System.out.println(urlNameString + "........");
//            URL realUrl = new URL(urlNameString);
//            // 打开和URL之间的连接
//            URLConnection connection = realUrl.openConnection();
//            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            // 建立实际的连接
//            connection.connect();
//            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
//            // 定义 BufferedReader输入流来读取URL的响应
//            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            while ((line = in.readLine()) != null) {
//                result += line;
//            }
//        } catch (Exception e) {
//            System.out.println("发送GET请求出现异常！" + e);
//            e.printStackTrace();
//        }
//        // 使用finally块来关闭输入流
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return result;
//    }

    public static Object httpPostWithJSON2(String url, String json, String id) throws Exception {
        // 将JSON进行UTF-8编码,以便传输中文
        InputStream instreams = null;
        String encoderJson = URLEncoder.encode(json, HTTP.UTF_8);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
        StringEntity se = new StringEntity(json);
        se.setContentType("application/json");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "UTF-8"));
        httpPost.setEntity(se);
        // httpClient.execute(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                instreams = resEntity.getContent();

            }
        }
        return instreams;
    }

    /**
     * 生成小程序二维码 -并保存到本地-返回二维码地址
     *
     *
     */
    /*
	 * post请求返回base64的图片数据
	 * */
    //
    public static String sendPost3(String url,String scode, String Checkcode) {
        PrintWriter out = null;
        String result = "";
        InputStream inputStream=null;
        //请求数据，自行拼接
        JsonObject params = new JsonObject();
        params.addProperty("scene",scode + Checkcode);
        params.addProperty("page","pages/index/index");
        params.addProperty("width",430);
        //System.out.println(params);
        System.out.println(params.toString());
        //String param = "scene="+uid+"&"+scode+"&page:pages/index/index&width:430";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
//			conn.setRequestProperty("accept", "*/*");
//			conn.setRequestProperty("connection", "Keep-Alive");
//			conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			conn.setCharacterEncoding("gbk");
            conn.setRequestProperty("Content-Type", "application/json;charset-gbk");
            conn.setRequestProperty("responseType", "arraybuffer");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            //获取流数据
            inputStream = conn.getInputStream();


            // 将获取流转为base64格式
            byte[] data = null;
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();

            result = new String(Base64.getEncoder().encode(data));
//			当import java.util.Base64;无法导入时，只能在网上找找其他的jar包，写法换成下面这种
//			result = new String(Base64.encodeBase64(data));


        }catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }

            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}

