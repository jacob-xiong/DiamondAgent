package diamond.agent.mvp.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2018-10-26.
 */

public class MemberLevelData implements Serializable {
    private List<LevelVO> level1;
    private List<LevelVO> level2;
    private List<LevelVO> level3;

    public List<LevelVO> getLevel1() {
        if(level1==null){
            level1=new ArrayList<>();
        }
        return level1 ;
    }

    public void setLevel1(List<LevelVO> level1) {
        this.level1 = level1;
    }

    public List<LevelVO> getLevel2() {
        if(level2==null){
            level2=new ArrayList<>();
        }
        return level2;
    }

    public void setLevel2(List<LevelVO> level2) {
        this.level2 = level2;
    }

    public List<LevelVO> getLevel3() {
        if(level3==null){
            level3=new ArrayList<>();
        }
        return level3;
    }

    public void setLevel3(List<LevelVO> level3) {
        this.level3 = level3;
    }
}
