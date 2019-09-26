package org.superbiz.moviefun.albumsapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private String AlbumsRepository;
    private RestOperations restOperations;

    public AlbumsClient(String AlbumsRepository, RestOperations restOperations) {
        this.AlbumsRepository = AlbumsRepository;
        this.restOperations = restOperations;
    }

    public void addAlbum(AlbumInfo albumInfo) {
        restOperations.postForEntity(AlbumsRepository, albumInfo, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        return restOperations.getForEntity(AlbumsRepository + "/" + id, AlbumInfo.class).getBody();
    }

    public List<AlbumInfo> getAlbums() {
        ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
        };

        return restOperations.exchange(AlbumsRepository, GET, null, albumListType).getBody();
    }
}