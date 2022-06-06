package MyOutput;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;


/**
 * @author lqb
 * @date 2021/10/11 9:55
 */
public class MyRecordWriter extends RecordWriter<Text, NullWritable> {

    private FSDataOutputStream goodCommentsOutputStream;
    private FSDataOutputStream badCommentsOutputStream;

    public MyRecordWriter(){}

    public MyRecordWriter(FSDataOutputStream goodCommentsOutputStream, FSDataOutputStream badCommentsOutputStream) {
        this.goodCommentsOutputStream = goodCommentsOutputStream;
        this.badCommentsOutputStream = badCommentsOutputStream;
    }

    @Override
    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        //好评字段
        String[] split = text.toString().split("\t");
        String number = split[9];
        //写入不同的文件夹文件
        if (Integer.parseInt(number) <= 1){
            goodCommentsOutputStream.write(text.toString().getBytes());
            goodCommentsOutputStream.write("\r\n".getBytes());
        }else {
            badCommentsOutputStream.write(text.toString().getBytes());
            badCommentsOutputStream.write("\r\n".getBytes());
        }
    }

    @Override
    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        IOUtils.closeStream(badCommentsOutputStream);
        IOUtils.closeStream(goodCommentsOutputStream);
    }
}
