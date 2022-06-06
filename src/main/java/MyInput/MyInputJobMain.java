package MyInput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author lqb
 * @date 2021/10/11 9:00
 */
public class MyInputJobMain extends Configured implements Tool {

    public int run(String[] strings) throws Exception {
        Job job = Job.getInstance(super.getConf(), MyInputJobMain.class.getSimpleName());
        job.setJarByClass(MyInputJobMain.class);
        //输入
        job.setInputFormatClass(MyInputFormat.class);
        MyInputFormat.addInputPath(job,new Path("hdfs://172.24.250.142:8020/myInputFormat_input"));
        //mapper
        job.setMapperClass(SequenceFileMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
        //reducer
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);
        //输出路径
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        SequenceFileOutputFormat.setOutputPath(job,new Path("hdfs://172.24.250.142:8020/myInputFormat_output"));
        boolean completion = job.waitForCompletion(true);
        return completion?0:1;
    }

    public static void main(String[] args) throws Exception{
        Configuration configuration = new Configuration();
        MyInputJobMain jobMain = new MyInputJobMain();
        int run = ToolRunner.run(configuration, jobMain, args);
        System.exit(run);
    }
}
