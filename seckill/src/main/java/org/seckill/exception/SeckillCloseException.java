package org.seckill.exception;

import org.seckill.dto.SeckillExecution;

/**
 * 秒杀关闭异常
 * Created by BBQ on 2017/5/3.
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
