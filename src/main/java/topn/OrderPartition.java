package topn;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author lqb
 * @date 2021/10/11 11:24
 */
public class OrderPartition extends Partitioner<OrderBean, Text> {
    @Override
    public int getPartition(OrderBean orderBean, Text text, int i) {
        return (orderBean.getOrderId().hashCode() & Integer.MAX_VALUE) % i;
    }
}
