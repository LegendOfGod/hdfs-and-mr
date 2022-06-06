package topn;

import org.apache.avro.Schema;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/11 11:12
 */
public class OrderBean implements WritableComparable<OrderBean> {

    private String orderId;
    private Double price;

    public OrderBean(){}

    public OrderBean(String orderId, Double price) {
        this.orderId = orderId;
        this.price = price;
    }

    public int compareTo(OrderBean o) {
        int i = this.orderId.compareTo(o.getOrderId());
        if (i == 0){
            return o.getPrice().compareTo(this.price);
        }
        return i;
    }

    @Override
    public String toString() {
        return orderId + "\t" + price;
    }

    //序列化
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeDouble(price);
    }

    //反序列化
    public void readFields(DataInput dataInput) throws IOException {
        this.orderId = dataInput.readUTF();
        this.price = dataInput.readDouble();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
