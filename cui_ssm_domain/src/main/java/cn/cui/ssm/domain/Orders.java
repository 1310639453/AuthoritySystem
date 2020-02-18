package cn.cui.ssm.domain;

import cn.cui.ssm.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther cui
 * @Date 2020/2/11
 * @Desc 订单信息
 */
public class Orders implements Serializable {
    private String id;  //主键
    private String orderNum;//编号
    private Date orderTime; //订单时间
    private String orderTimeStr;
    private Integer peopleCount;
    private String orderDesc;   //订单描述
    private Integer payType;    //支付类型
    private String payTypeStr;
    private Integer orderStatus;//订单状态
    private String orderStatusStr;
    private String productId;   //产品id
    private String memberId;    //会员id
    //订单所属的产品
    private Product product;
    //会员信息
    private Member member;
    //旅客信息
    private List<Traveller> travellers;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPayTypeStr() {
        if(payType!=null){
            if(payType==0){
                payTypeStr="支付宝";
            }else if(payType==1){
                payTypeStr="微信";
            }else if(payType==2){
                payTypeStr="其它";
            }
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderStatusStr() {
        if(orderStatus!=null){
            if(orderStatus==1){
                orderStatusStr = "已支付";
            } else if(orderStatus==0){
                orderStatusStr = "未支付";
            }
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if(orderTime!=null){
            String dateStr = DateUtils.date2String(orderTime, "yyyy-MM-dd HH:mm:ss");
            orderTimeStr = dateStr;
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public Integer getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
