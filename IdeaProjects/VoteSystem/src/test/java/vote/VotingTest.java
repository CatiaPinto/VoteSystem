package vote;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class VotingTest {

    private static Map<String, Integer> poll = new HashMap();
    private static String voter = "voterR";
    private static String candidateA = "A";
    private static String candidateB = "B";

    static {
        poll.put("A", 8000000);
        poll.put("B", 2000000);
        poll.put("C", 6000000);
        poll.put("D", 4000000);
    }

    private Voting voting = new Voting();

    @Test
    public void shouldReturnCandidateAWhenMaxVotes() {
        assertEquals("max should be candidate A", "A", this.voting.getOverallResult(poll));
    }

    @Test
    public void shouldReturnTrueWhenVoterAOnlyVotedOnce() {
        voting.voterIsRegistered(voter);
        voting.voteToCandidate(candidateA, voter);
        assertTrue(this.voting.userCanVote(voter));
    }

    @Test
    public void shouldReturnFalseWhenVoterVotes3Times() {
        voting.voterIsRegistered(voter);
        voting.voteToCandidate(candidateA, voter);
        voting.voteToCandidate(candidateA, voter);
        voting.voteToCandidate(candidateA, voter);
        assertFalse(this.voting.userCanVote(voter));
    }

    @Test
    public void shouldReturnFalseWhenVoterVotes1TimesCandidateAPlusTimeCandidateB() {
        voting.voterIsRegistered(voter);
        voting.voteToCandidate(candidateA, voter);
        voting.voteToCandidate(candidateA, voter);
        voting.voteToCandidate(candidateB, voter);
        assertFalse(this.voting.userCanVote(voter));
    }

}
