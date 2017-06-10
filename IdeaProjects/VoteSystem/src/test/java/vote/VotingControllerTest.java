package vote;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VotingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void validRequestShouldReturn201() throws Exception {
        this.mockMvc.perform(get("/vote?candidate=A&voter=A"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Your vote was successfully registered")));
    }

    @Test
    public void whenReceiveMoreThenThreeVotesShouldReturn403() throws Exception {
        this.mockMvc.perform(get("/vote?candidate=A&voter=B"));
        this.mockMvc.perform(get("/vote?candidate=A&voter=B"));
        this.mockMvc.perform(get("/vote?candidate=A&voter=B"));
        this.mockMvc.perform(get("/vote?candidate=A&voter=B"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("Sorry! You can only vote 3 times")));
    }

    @Test
    public void whenReceiveMoreThenThreeVotesShouldReturn403RegardlessOfCandidate() throws Exception {
        this.mockMvc.perform(get("/vote?candidate=A&voter=C"));
        this.mockMvc.perform(get("/vote?candidate=A&voter=C"));
        this.mockMvc.perform(get("/vote?candidate=D&voter=C"));
        this.mockMvc.perform(get("/vote?candidate=D&voter=C"))
                .andExpect(status().isForbidden())
                .andExpect(content().string(containsString("Sorry! You can only vote 3 times")));
    }

}
