package subway.application;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import subway.dao.StationDao;
import subway.domain.Station;
import subway.dto.StationRequest;
import subway.dto.StationResponse;

@Service
public class StationService {
    private final StationDao stationDao;

    public StationService(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    public StationResponse saveStation(StationRequest stationRequest) {
        stationDao.findByName(stationRequest.getName())
                .ifPresent(station -> {
                    throw new IllegalArgumentException(
                            MessageFormat.format("{0}에 해당하는 station이 이미 존재합니다.", stationRequest.getName()));
                });

        Station station = stationDao.insert(new Station(stationRequest.getName()));
        return StationResponse.of(station);
    }

    public StationResponse findStationResponseById(Long id) {
        Station station = stationDao.findById(id).orElseThrow(() -> new IllegalStateException(
                MessageFormat.format("station id \"{0}\"에 해당하는 station이 없습니다.", id)));

        return StationResponse.of(station);
    }

    public List<StationResponse> findAllStationResponses() {
        List<Station> stations = stationDao.findAll();

        return stations.stream()
                .map(StationResponse::of)
                .collect(Collectors.toList());
    }

    public void updateStation(Long id, StationRequest stationRequest) {
        stationDao.update(new Station(id, stationRequest.getName()));
    }

    public void deleteStationById(Long id) {
        stationDao.deleteById(id);
    }
}