package me.frank.manager.server.mapper.bo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "file_info")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件后缀名
     */
    @Column(name = "file_type")
    private String fileType;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private String fileSize;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件md5，用于区别文件是否已存在
     */
    private String md5;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件后缀名
     *
     * @return file_type - 文件后缀名
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置文件后缀名
     *
     * @param fileType 文件后缀名
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * 获取文件大小
     *
     * @return file_size - 文件大小
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     *
     * @param fileSize 文件大小
     */
    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 获取文件路径
     *
     * @return path - 文件路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置文件路径
     *
     * @param path 文件路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取文件md5，用于区别文件是否已存在
     *
     * @return md5 - 文件md5，用于区别文件是否已存在
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 设置文件md5，用于区别文件是否已存在
     *
     * @param md5 文件md5，用于区别文件是否已存在
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}