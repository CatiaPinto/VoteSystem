package vote;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the voting system.
 */
public class Voting {

    //there is only one poll
    private static Map<String, Integer> votesPoll = new HashMap();
    //there are only four candidates
    // for simplicity and readability candidates are identified by name
    static {
        votesPoll.put("A", 0);
        votesPoll.put("B", 0);
        votesPoll.put("C", 0);
        votesPoll.put("D", 0);
    }

    // for simplicity and readability candidates are identified by name
    private Map<String, Integer> userVotes = new HashMap();

    /**
     * Determines if the candidate selected is valid, by checking if the name given in the
     * URL is present in the poll of candidates.
     * @param candidate the candidate name.
     * @return true if the candidate is in the pool.
     */
    public boolean isValidCandidate(String candidate){

        return votesPoll.containsKey(candidate);

    }

    /**
     * Add a vote to the selected candidate.
     * Mark the voter.
     * @param vCandidate the candidate voted in.
     * @param voter the voter that made that vote.
     * @return the poll with all the votes.
     */
    public Map<String, Integer> voteToCandidate(String vCandidate, String voter) {

        votesPoll.compute(vCandidate, (k, v) -> v + 1);
        userVotes.compute(voter, (k, v) -> v + 1);
        return votesPoll;
    }

    /**
     * Determine if the voter can vote.
     * @param voter the voter.
     * @return true if the user can vote.
     */
    public boolean userCanVote(String voter) {

        // A voter cannot vote more than 3 times
        return userVotes.get(voter) <= 3;

    }

    /**
     * Get the winner according to the number of votes.
     * @param candidatesPoll the candidates poll.
     * @return the candidate with most votes. If there are two candidates with the same number of votes,
     * the first one encountered is the one retrieved.
     */
    public String getOverallResult(Map<String, Integer> candidatesPoll) {
        if (candidatesPoll.isEmpty()) {
            return "There were no votes and no winners";
        }
        return candidatesPoll.entrySet().stream()
                .max(Map.Entry.comparingByValue(Integer::compareTo)).get().getKey();
    }

    /**
     * Make sure that the voter is registered.
     * @param voterName the voter.
     * @return true if the voter is registered.
     */
    public boolean voterIsRegistered(String voterName) {

        //replace with the voter validation
        //only if the user is registered is added to the userVotes map
        if (!userVotes.containsKey(voterName)) {
            userVotes.put(voterName, 1);
        }
        //Assumption that all voters are registered
        return true;
    }

    /**
     * Get the votes poll.
     * @return the votes poll.
     */
    public Map<String, Integer> getVotesPoll() {
        return votesPoll;
    }
}
