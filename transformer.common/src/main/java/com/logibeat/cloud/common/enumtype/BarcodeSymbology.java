package com.logibeat.cloud.common.enumtype;

public enum BarcodeSymbology {

	   
    
    AZTEC(1,"AZTEC"),
    CODABAR(2,"CODABAR"),
    CODE_39(3,"CODE_39"),
    CODE_93(4,"CODE_93"),
    CODE_128(5,"CODE_128"),
    DATA_MATRIX(6,"DATA_MATRIX"),
    EAN_8(7,"EAN_8"),
    EAN_13(8,"EAN_13"),
    ITF(9,"ITF"),
    MAXICODE(10,"MAXICODE"),
    PDF_417(11,"PDF_417"),
    QR_CODE(12,"QR_CODE"),
    RSS_14(13,"RSS_14"),
    RSS_EXPANDED(14,"RSS_EXPANDED"),
    UPC_A(15,"UPC_A"),
    UPC_E(16,"UPC_E"),
    UPC_EAN_EXTENSION(17,"UPC_EAN_EXTENSION");
    
    
//  /** 0: 未知（全部） */
//  public final static int Unknown = 0;
//  /** 1: 待派单(未派单) */
//  public final static int WaitEntrust = 1;
//  /** 2: 未接单 */
//  public final static int WaitCarrier = 2;
//  /** 3: 待派车(已派单) */
//  public final static int WaitCar = 3;
//  /** 4: 待发车（已派单） */
//  public final static int WaitRun = 4;
//  /** 5: 执行中（在途） */
//  public final static int Runing = 5;
//  /** 6: 已到达 */
//  public final static int Arrive = 6;
//  /** 7: 已完成 */
//  public final static int Finish = 7;
         
    protected Integer  value;

    protected String  description;


    /**
     * 构造函数
     *
     * @param code
     * @param desc
     */
    BarcodeSymbology(Integer value, String description)
    {
        this.value = value;
        this.description = description;
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
    
    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return value.toString();
    }
    
    

}
