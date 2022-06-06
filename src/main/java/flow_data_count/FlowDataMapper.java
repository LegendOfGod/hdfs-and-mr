package flow_data_count;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/8 19:10
 */
public class FlowDataMapper extends Mapper<LongWritable, Text,Text,FlowData> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String data = value.toString();
        String[] split = data.split("\\s+");
        //流量字段
        FlowData flowData = new FlowData();
        flowData.setUpFlow(Integer.valueOf(split[6]));
        flowData.setDownFlow(Integer.valueOf(split[7]));
        flowData.setUpCountFlow(Integer.valueOf(split[8]));
        flowData.setDownCountFlow(Integer.valueOf(split[9]));
        //输出
        context.write(new Text(split[1]),flowData);
    }
}
