package com.logibeat.cloud.common.enumtype;

/**
 * Created by wilson on 2018/1/11.
 */
public enum PlateColor {

    UNKNOWN(0, "黄色",2),
    YELLOW(1, "黄色",2),
    BLUE(2, "蓝色",1),
    GREEN(3, "绿色",5),
    KELLY(4, "黄绿色",93),
    BLACK(5, "黑色",3),
    WHITE(6, "白色",4),
    AGRICULTURALYELLOW(7, "农黄色",91),
    AGRICULTURALGREEN(8, "农绿色",92),
    GRADIENTGREEN(9, "渐变绿",94),
    OTHER(10, "其他",9);

    protected Integer  value;

    protected String  description;

    private Integer starSoftCode;




    /**
     * 构造函数
     *
     * @param value
     * @param description
     */
    PlateColor(Integer value, String description,Integer starSoftCode)
    {
        this.value = value;
        this.description = description;
        this.starSoftCode = starSoftCode;
    }


    public Integer getValue()
    {
        return value;
    }

    public void setValue(Integer value)
    {
        this.value = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Integer getStarSoftCode() {
        return starSoftCode;
    }

    public void setStarSoftCode(Integer starSoftCode) {
        this.starSoftCode = starSoftCode;
    }

    public static PlateColor getSelfByValue(String value){
        PlateColor result = null;
        switch (value) {
            case "0":
                result = PlateColor.UNKNOWN;
                break;
            case "1":
                result = PlateColor.YELLOW;
                break;
            case "2":
                result = PlateColor.BLUE;
                break;
            case "3":
                result = PlateColor.GREEN;
                break;
            case "4":
                result = PlateColor.KELLY;
                break;
            case "5":
                result = PlateColor.BLACK;
                break;
            case "6":
                result = PlateColor.WHITE;
                break;
            case "7":
                result = PlateColor.AGRICULTURALYELLOW;
                break;
            case "8":
                result = PlateColor.AGRICULTURALGREEN;
                break;
            case "9":
                result = PlateColor.GRADIENTGREEN;
                break;
            case "10":
                result = PlateColor.OTHER;
                break;
        }
        return result;
    }

}
