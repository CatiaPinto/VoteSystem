package vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class VotingController {

    Voting voting = new Voting();
    private static final String template = "The winner is, %s!";

    @RequestMapping("/vote")
    public ResponseEntity<?> voting(@RequestParam(value = "voter") String voterName,
                                    @RequestParam(value = "candidate") String candidateName ) {
        if (!voting.voterAreRegistered(voterName)){
            return new ResponseEntity<Object>("Sorry! You need to register before voting", HttpStatus.FORBIDDEN);
        }

        if (!voting.isValidCandidate(candidateName)){
            return new ResponseEntity<Object>("That candidate is not valid", HttpStatus.BAD_REQUEST);
        }

        if(!voting.userCanVote(voterName)){
            return new ResponseEntity<Object>("Sorry! You can only vote 3 times", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<Object>("Your vote was successfully registered"
                + voting.voteToCandidate(candidateName, voterName), HttpStatus.CREATED);
    }

    @RequestMapping("/result")
    public ResponseEntity<?> getResult() {
        String winner = voting.getOverallResult(voting.getVotesPoll());
        String pool = Arrays.toString(voting.getVotesPoll().entrySet().toArray());
        return new ResponseEntity<Object>(
                String.format(template, winner) + pool, HttpStatus.OK);
    }


}
