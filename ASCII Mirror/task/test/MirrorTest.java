import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MirrorTest extends StageTest {

  String[] test_data = {
          " ","test","C:\\Users\\Examples\\test.txt","/home/username/test.txt"
  };

  @DynamicTest(data = "test_data")
  CheckResult testSolution(String input) {
    TestedProgram pr = new TestedProgram();
    String output = pr.start().strip().toLowerCase();
    String[] strings = output.strip().split("\n");
    List<String> list = new ArrayList<>(Arrays.asList(strings));
    list.removeAll(Arrays.asList(""));
    if(list.size()!=1 || !list.get(0).contains("file path")){
      throw new WrongAnswer("When the program just started, output should contain exactly 1 non-empty line " +
              "with \"file path\" substring");
    }
    output = pr.execute(input);
    strings = output.split("\n");
    list = new ArrayList<>(Arrays.asList(strings));
    list.removeAll(Arrays.asList(""));
    if(list.size()!=6 || !list.get(0).contains(input)){
      throw new WrongAnswer("When the user entered any string, program should output exactly 6 non-empty" +
              " lines, first one should contain user's input, other 5 ones should contain ascii cow's picture");
    }
    List<String> correctList = new ArrayList<>(Arrays.asList(
            "            ^__^",
            "    _______/(oo)",
            "/\\/(       /(__)",
            "   | w----||    ",
            "   ||     ||    "));
    for (int i=1;i<list.size();i++){
      if(!list.get(i).equals(correctList.get(i-1))){
        throw new WrongAnswer("All the printed lines in ascii cow's picture should be equal to lines in the " +
                " example. Make sure, that you've not ignored whitespaces at the beginning and at the end of each line");
      }
    }
    if(!pr.isFinished()){
      throw new WrongAnswer("Program should finish after outputting the picture");
    }
    return CheckResult.correct();
  }
}
