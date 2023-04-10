import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.exception.outcomes.WrongAnswer;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class MirrorTest extends StageTest {
  Object[][] test_data = {
          {"./test/example1.txt",1},
          {"./test/example2.txt",1},
          {"./test/example3.txt",1},
          {"./test/example4.txt",1},
          {"./test/example5.txt",1},
          {"./test/example6.txt",0},
          {"./test/example4",0},
          {"./test/???",0},
          {"./test",0}
  };

  @DynamicTest(data = "test_data")
  CheckResult testSolution(String input, int result) {
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

    if(result==0){
      output = output.toLowerCase();
      strings = output.split("\n");
      list = new ArrayList<>(Arrays.asList(strings));
      list.removeAll(Arrays.asList(""));
      if(list.size()!=1 || !list.get(0).contains("file not found")){
        throw new WrongAnswer("When the user inputs a file, that can not be correctly opened - program " +
                "should inform user, by outputting an one-line error with  \"File not found\" substring");
      }
    }

    if(result==1){
      strings = output.split("\n");
      list = new ArrayList<>(Arrays.asList(strings));

      List<String> file_str = new ArrayList<>();

      File file = new File(input);

      try {
        file_str= Files.readAllLines(file.toPath());
      } catch (IOException io){ }

      if(list.size() != file_str.size()) {
        throw new WrongAnswer("When the user inputs a file, that can be correctly opened - output should " +
                "contain as much lines, as there were in file.");
      }

      for (int i=0;i<list.size();i++) {
        if (!list.get(i).equals(file_str.get(i))) {
          throw new WrongAnswer("When the user inputs a file, that can be correctly opened - your program " +
                  "should output all the text from that file line by line");
        }
      }
    }
    if(!pr.isFinished()){
      throw new WrongAnswer("Program should finish after outputting the picture");
    }
    return CheckResult.correct();
  }
}