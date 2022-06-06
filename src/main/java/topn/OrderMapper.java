package topn;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/11 11:19
 */
public class OrderMapper extends Mapper<LongWritable, Text,OrderBean,Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        String orderId = split[0];
        Double price = Double.valueOf(split[2]);
        OrderBean orderBean = new OrderBean(orderId, price);
        context.write(orderBean,value);
    }
}
