package org.lanadvice;

import io.quarkus.test.junit.NativeImageTest;
import org.lanadvice.rest.QuestionnaireControllerTest;

@NativeImageTest
public class NativeExampleResourceIT extends QuestionnaireControllerTest {

    // Execute the same tests but in native mode.
}
