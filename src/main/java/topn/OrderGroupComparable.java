package topn;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author lqb
 * @date 2021/10/11 11:33
 */
public class OrderGroupComparable extends WritableComparator {

    public OrderGroupComparable(){
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean first = (OrderBean) a;
        OrderBean second = (OrderBean) b;
        return first.getOrderId().compareTo(second.getOrderId());
    }

}
