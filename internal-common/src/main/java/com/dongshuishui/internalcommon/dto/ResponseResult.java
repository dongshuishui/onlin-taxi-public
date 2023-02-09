package com.dongshuishui.internalcommon.dto;

import com.dongshuishui.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther: 东水水
 * @Date: 2023/2/9  12:46
 * @Description: com.dongshuishui.internalcommon.dto
 * @Version: 1.0
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 成功相应的方法
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult success(T data){
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getValue())
                .setData(data);
    }

    /**
     * 统一的失败
     * @param data
     * @return
     * @param <T>
     */
    public static <T> ResponseResult fail(T data){
        return new ResponseResult().setData(data);
    }
    /**
     * 自定义失败
     * @param code
     * @param message
     * @return
     */
    public static ResponseResult fail(int code, String message){
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 自定义失败：错误码，提示信息，具体错误
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResponseResult fail(int code, String message,String data){
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }
}
