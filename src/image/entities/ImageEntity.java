/**
 * ImageEntity.java, 2021/07/15
 * 
 */
package image.entities;

import image.utils.Constant;

/**
 * @Description 
 * Đối tượng lưu thông tin của một ảnh
 *
 * @Author AI_TEAM
 * @Version 1.0
 */
public class ImageEntity {
    private int imageId;
    private String imageName;
    private String alternateText;
    
    @Override
    public boolean equals(Object obj) {
        ImageEntity image = (ImageEntity) obj;
        return this.imageName.equals(image.getImageName());
    }
    
    @Override
    public String toString() {
        return imageName + Constant.IMAGE_DELIMITER + alternateText + Constant.LINE_DELIMITER;
    }
    
    /**
     * @return the imageId
     */
    public int getImageId() {
        return imageId;
    }
    /**
     * @param imageId the imageId to set
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }
    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    /**
     * @return the alternateText
     */
    public String getAlternateText() {
        return alternateText;
    }
    /**
     * @param alternateText the alternateText to set
     */
    public void setAlternateText(String alternateText) {
        this.alternateText = alternateText;
    }
}
