package com.rqh.system.utils.DataDecryption;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rqh.system.bean.SessionKeyBean;
import com.rqh.system.utils.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataDecryption {

    private String appid;
    private String secret;

    public DataDecryption(String appid,String secret){
        this.appid=appid;
        this.secret=secret;
    }

//https://api.weixin.qq.com/sns/jscode2session?appid=wx5fa22712056d8803&secret=a24fd306724ac99949601996c647ecbb&js_code=
    public  String sendGet(String code) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code;
            URL realUrl = new URL(urlNameString);
            System.out.println(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

            System.out.println(result+"fff");
            if(result.contains("errcode")){
                return null;
            }
            System.out.println(result+"fff");

            SessionKeyBean bean= JSONUtil.parse(result,SessionKeyBean.class);

            System.out.println(result+"fff");
            result=bean.getSession_key();

            System.out.println(result+"fff");
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            return null;
        }
        // 使用finally块来关闭输入流
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



    public String decryptData(String encryptedData, String iv,String sessionKey)
    {
        if (StringUtils.length(sessionKey) != 24)
        {
            return "ErrorCode::$IllegalAesKey;";
        }
        // 对称解密秘钥 aeskey = Base64_Decode(session_key), aeskey 是16字节。
        byte[] aesKey = Base64.decodeBase64(sessionKey);

        if (StringUtils.length(iv) != 24)
        {
            return "ErrorCode::$IllegalIv;";
        }
        // 对称解密算法初始向量 为Base64_Decode(iv)，其中iv由数据接口返回。
        byte[] aesIV = Base64.decodeBase64(iv);

        // 对称解密的目标密文为 Base64_Decode(encryptedData)
        byte[] aesCipher = Base64.decodeBase64(encryptedData);

        Map<String, String> map = new HashMap<>();

        try
        {
            byte[] resultByte = AESUtils.decrypt(aesCipher, aesKey, aesIV);

            if (null != resultByte && resultByte.length > 0)
            {
                String userInfo = new String(resultByte, "UTF-8");
                map.put("code", "0000");
                map.put("msg", "succeed");
                map.put("userInfo", userInfo);

                // watermark参数说明：
                // 参数  类型  说明
                // watermark   OBJECT  数据水印
                // appid   String  敏感数据归属appid，开发者可校验此参数与自身appid是否一致
                // timestamp   DateInt 敏感数据获取的时间戳, 开发者可以用于数据时效性校验'

                // 根据微信建议：敏感数据归属appid，开发者可校验此参数与自身appid是否一致
                // if decrypted['watermark']['appid'] != self.appId:
                JSONObject jsons = JSON.parseObject(userInfo);
                String id = jsons.getJSONObject("watermark").getString("appid");
                if(!StringUtils.equals(id, appid))
                {
                    return "ErrorCode::$IllegalBuffer;";
                }
            }
            else
            {
                map.put("status", "1000");
                map.put("msg", "false");
            }
        }
        catch (InvalidAlgorithmParameterException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

}
