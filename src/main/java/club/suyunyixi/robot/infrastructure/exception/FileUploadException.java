package club.suyunyixi.robot.infrastructure.exception;

/**
 * 当资源上传腾讯服务器失败时抛出
 *
 * @author Suyunyixi
 * @description
 * @date 2023/2/6
 */
public class FileUploadException extends Exception {
    public FileUploadException(String message) {
        super(message);
    }
}