package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
@RequiredArgsConstructor
public class BlackListSearchThread  extends Thread{
    private final int start;
    private final int end;
    private final String ip;
    private final List<Integer> occurrences = new LinkedList<>();
    private int count = 0;

    @Override
    public void run() {
        HostBlacklistsDataSourceFacade skds =
                HostBlacklistsDataSourceFacade.getInstance();

        IntStream.range(start, end).forEach(k -> {
            count++;
            if (skds.isInBlackListServer(k, ip)) {
                occurrences.add(k);
            }
        });
    }

}
