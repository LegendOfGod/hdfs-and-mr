package flow_data_count;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/8 19:18
 */
public class FlowDataReducer extends Reducer<Text,FlowData,Text,FlowData> {
    @Override
    protected void reduce(Text key, Iterable<FlowData> values, Context context) throws IOException, InterruptedException {
        FlowData flowData = new FlowData();
        Integer upFlow = 0;
        Integer downFlow = 0;
        Integer upCountFlow = 0;
        Integer downCountFlow = 0;
        for (FlowData data : values) {
            upFlow += data.getUpFlow();
            downFlow += data.getDownFlow();
            upCountFlow += data.getUpCountFlow();
            downCountFlow += data.getDownCountFlow();
        }
        flowData.setUpFlow(upFlow);
        flowData.setDownFlow(downFlow);
        flowData.setUpCountFlow(upCountFlow);
        flowData.setDownCountFlow(downCountFlow);
        context.write(key,flowData);
    }
}
