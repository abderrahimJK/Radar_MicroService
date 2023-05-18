package ma.enset.radarservice.web;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import ma.enset.radarservice.entities.Radar;
import ma.enset.radarservice.mappers.RadarMapper;
import ma.enset.radarservice.repository.RadarRepository;
import ma.enset.radarservice.web.grpc.stub.RadarGrpcServiceGrpc;
import ma.enset.radarservice.web.grpc.stub.RadarOuterClass;
import net.devh.boot.grpc.server.service.GrpcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@AllArgsConstructor
public class RadarGrpcService extends RadarGrpcServiceGrpc.RadarGrpcServiceImplBase {

    RadarRepository radarRepository;
    RadarMapper radarMapper;

    @Override
    public void saveRadar(RadarOuterClass.SaveRadarRequest request, StreamObserver<RadarOuterClass.SaveRadarResponse> responseObserver) {
        Radar radar = new Radar().builder()
                .id(null)
                .vitesseMax(Double.parseDouble(request.getVitesseMax()))
                .longitude(Double.parseDouble(request.getLongitude()))
                .latitude(Double.parseDouble(request.getLatitude()))
                .build();

        radarRepository.save(radar);
        RadarOuterClass.Radar grpcRadar = radarMapper.fromClient(radar);
        RadarOuterClass.SaveRadarResponse radarsResponse = RadarOuterClass.SaveRadarResponse.newBuilder()
                .setRadar(grpcRadar)
                .build();
        responseObserver.onNext(radarsResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getRadar(RadarOuterClass.GetRadarRequest request, StreamObserver<RadarOuterClass.GetRadarResponse> responseObserver) {
        Radar radar = radarRepository.findById(request.getId()).get();
        RadarOuterClass.Radar grpcRadar = radarMapper.fromClient(radar);
        RadarOuterClass.GetRadarResponse radarsResponse = RadarOuterClass.GetRadarResponse.newBuilder()
                .setRadar(grpcRadar)
                .build();
        responseObserver.onNext(radarsResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getListRadars(RadarOuterClass.GetAllRadarsRequest request, StreamObserver<RadarOuterClass.GetAllRadarsResponse> responseObserver) {
        List<Radar> radarList = radarRepository.findAll();
        RadarOuterClass.GetAllRadarsResponse.Builder radarBuilder = RadarOuterClass.GetAllRadarsResponse.newBuilder();
        List<RadarOuterClass.Radar> radars = radarList.stream().map(radarMapper::fromClient).collect(Collectors.toList());
        radarBuilder.addAllRadars(radars);
        responseObserver.onNext(radarBuilder.build());
        responseObserver.onCompleted();
    }
}
