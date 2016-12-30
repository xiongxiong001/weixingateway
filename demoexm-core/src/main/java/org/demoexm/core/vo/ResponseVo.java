package org.demoexm.core.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ResponseVo implements Serializable{
    
    private int errcode;
    
    private String errmsg;
    
    private String descript;
    
    public int getErrcode()
    {
        return errcode;
    }
    
    public void setErrcode(int errcode)
    {
        this.errcode = errcode;
    }
    
    public String getErrmsg()
    {
        return errmsg;
    }
    
    public void setErrmsg(String errmsg)
    {
        this.errmsg = errmsg;
    }
    
    public String getDescript()
    {
        return descript;
    }
    
    public void setDescript(String descript)
    {
        this.descript = descript;
    }
    
}
