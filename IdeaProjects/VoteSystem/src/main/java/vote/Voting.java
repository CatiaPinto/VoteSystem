package vote;

import java.util.HashMap;
import java.util.Map;

public class Voting {

    //there is only one poll
    private static Map<String, Integer> votesPoll = new HashMap();
    //there are only four candidates
    static {
        votesPoll.put("A", 0);
        votesPoll.put("B", 0);
        votesPoll.put("C", 0);
        votesPoll.put("D", 0);
    }

    private Map<String, Integer> userVotes = new HashMap();

    public boolean isValidCandidate(String candidate){

        return votesPoll.containsKey(candidate);

    }

    public Map<String, Integer> voteToCandidate(String vCandidate, String voter) {

        if (votesPoll.containsKey(vCandidate)) {
            votesPoll.compute(vCandidate, (k, v) -> v + 1);
        } else {
            votesPoll.put(vCandidate, 1);
        }
        userVotes.compute(voter, (k, v) -> v + 1);
        return votesPoll;
    }

    public boolean userCanVote(String voter) {

        // A voter cannot vote more than 3 times
        return userVotes.get(voter) <= 3;

    }

    public String getOverallResult(Map<String, Integer> candidatesVotes) {
        if (candidatesVotes.isEmpty()) {
            return "There were no votes and no winners";
        }
        return candidatesVotes.entrySet().stream()
                .max(Map.Entry.comparingByValue(Integer::compareTo)).get().getKey();
    }

    public boolean voterAreRegistered(String voterName) {

        //replace with the voter validation
        //only if the user is registered is added to the userVotes map
        if (!userVotes.containsKey(voterName)) {
            userVotes.put(voterName, 1);
        }
        //Assumption that all voters are registered
        return true;
    }

    public Map<String, Integer> getVotesPoll() {
        return votesPoll;
    }
}
