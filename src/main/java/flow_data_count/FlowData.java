package flow_data_count;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author lqb
 * @date 2021/10/8 19:03
 */
public class FlowData implements Writable {

    private Integer upFlow;
    private Integer downFlow;
    private Integer upCountFlow;
    private Integer downCountFlow;

    public FlowData(){}

    public FlowData(Integer upFlow, Integer downFlow, Integer upCountFlow, Integer downCountFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.upCountFlow = upCountFlow;
        this.downCountFlow = downCountFlow;
    }

    //
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.upFlow);
        dataOutput.writeInt(this.downFlow);
        dataOutput.writeInt(this.upCountFlow);
        dataOutput.writeInt(this.downCountFlow);
    }

    //反序列化
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow = dataInput.readInt();
        this.downFlow = dataInput.readInt();
        this.upCountFlow = dataInput.readInt();
        this.downCountFlow = dataInput.readInt();
    }

    @Override
    public String toString() {
        return "FlowData{" +
                "upFlow=" + upFlow +
                ", downFlow=" + downFlow +
                ", upCountFlow=" + upCountFlow +
                ", downCountFlow=" + downCountFlow +
                '}';
    }

    public Integer getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public Integer getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public Integer getUpCountFlow() {
        return upCountFlow;
    }

    public void setUpCountFlow(Integer upCountFlow) {
        this.upCountFlow = upCountFlow;
    }

    public Integer getDownCountFlow() {
        return downCountFlow;
    }

    public void setDownCountFlow(Integer downCountFlow) {
        this.downCountFlow = downCountFlow;
    }
}
