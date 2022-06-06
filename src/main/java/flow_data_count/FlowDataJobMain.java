package flow_data_count;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author lqb
 * @date 2021/10/8 19:23
 */
public class FlowDataJobMain extends Configured implements Tool {

    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), FlowDataJobMain.class.getSimpleName());
        job.setJarByClass(FlowDataJobMain.class);
        //输入
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job,new Path("hdfs://172.24.250.143:8020/data_flow"));
        //mapper
        job.setMapperClass(FlowDataMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowData.class);
        //reduce
        job.setReducerClass(FlowDataReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowData.class);
        //输出文件位置
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job,new Path("hdfs://172.24.250.143:8020/flow_data_out"));
        //任务
        boolean completion = job.waitForCompletion(true);
        return completion?0:1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        FlowDataJobMain jobMain = new FlowDataJobMain();
        int run = ToolRunner.run(configuration, jobMain, args);
        System.exit(run);
    }
}
