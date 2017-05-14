package org.seckill.exception;

import org.seckill.dto.SeckillExecution;

/**
 * 重复秒杀异常(运行期   异常)
 * Created by BBQ on 2017/5/3.
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
