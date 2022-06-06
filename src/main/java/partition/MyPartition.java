package partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author lqb
 * @date 2021/10/8 16:19
 */
public class MyPartition extends Partitioner<Text, NullWritable> {
    @Override
    public int getPartition(Text text, NullWritable nullWritable, int i) {
        System.out.println(text.toString()+":"+text.toString().length());
        if (text.toString().length() >= 5){
            return 0;
        }else {
            return 1;
        }
    }
}
