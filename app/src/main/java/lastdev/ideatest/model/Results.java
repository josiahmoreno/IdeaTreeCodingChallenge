package lastdev.ideatest.model;

import java.util.List;

/**
 * Created by Josiah on 6/21/2017.
 * POJO of the Json Results from the Itunes Api
 *
 * P.S Probably Should of Name Results.Result to Track or Song
 */

public class Results {
    public static final double ALBUM_PURCHASE_ONLY = -1.00;
    private int resultCount;
    private List<Result> results;
    public static String TYPE_SONG = "song";

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public static class Result {
        private String wrapperType;
        private String kind;
        private int artistId;
        private int collectionId;
        private int trackId;
        private String artistName;
        private String collectionName;
        private String trackName;
        private String collectionCensoredName;
        private String trackCensoredName;
        private String artistViewUrl;
        private String collectionViewUrl;
        private String trackViewUrl;
        private String previewUrl;
        private String artworkUrl30;
        private String artworkUrl60;
        private String artworkUrl100;
        private double collectionPrice;
        private double trackPrice;
        private String releaseDate;
        private String collectionExplicitness;
        private String trackExplicitness;
        private int discCount;
        private int discNumber;
        private int trackCount;
        private int trackNumber;
        private int trackTimeMillis;
        private String country;
        private String currency;
        private String primaryGenreName;
        private boolean isStreamable;
        private String collectionArtistName;
        private double trackRentalPrice;
        private double collectionHdPrice;
        private double trackHdPrice;
        private double trackHdRentalPrice;
        private String contentAdvisoryRating;
        private boolean hasITunesExtras;

        public String getWrapperType() {
            return wrapperType;
        }

        public void setWrapperType(String wrapperType) {
            this.wrapperType = wrapperType;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public int getArtistId() {
            return artistId;
        }

        public void setArtistId(int artistId) {
            this.artistId = artistId;
        }

        public int getCollectionId() {
            return collectionId;
        }

        public void setCollectionId(int collectionId) {
            this.collectionId = collectionId;
        }

        public int getTrackId() {
            return trackId;
        }

        public void setTrackId(int trackId) {
            this.trackId = trackId;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getCollectionName() {
            return collectionName;
        }

        public void setCollectionName(String collectionName) {
            this.collectionName = collectionName;
        }

        public String getTrackName() {
            return trackName;
        }

        public void setTrackName(String trackName) {
            this.trackName = trackName;
        }

        public String getCollectionCensoredName() {
            return collectionCensoredName;
        }

        public void setCollectionCensoredName(String collectionCensoredName) {
            this.collectionCensoredName = collectionCensoredName;
        }

        public String getTrackCensoredName() {
            return trackCensoredName;
        }

        public void setTrackCensoredName(String trackCensoredName) {
            this.trackCensoredName = trackCensoredName;
        }

        public String getArtistViewUrl() {
            return artistViewUrl;
        }

        public void setArtistViewUrl(String artistViewUrl) {
            this.artistViewUrl = artistViewUrl;
        }

        public String getCollectionViewUrl() {
            return collectionViewUrl;
        }

        public void setCollectionViewUrl(String collectionViewUrl) {
            this.collectionViewUrl = collectionViewUrl;
        }

        public String getTrackViewUrl() {
            return trackViewUrl;
        }

        public void setTrackViewUrl(String trackViewUrl) {
            this.trackViewUrl = trackViewUrl;
        }

        public String getPreviewUrl() {
            return previewUrl;
        }

        public void setPreviewUrl(String previewUrl) {
            this.previewUrl = previewUrl;
        }

        public String getArtworkUrl30() {
            return artworkUrl30;
        }

        public void setArtworkUrl30(String artworkUrl30) {
            this.artworkUrl30 = artworkUrl30;
        }

        public String getArtworkUrl60() {
            return artworkUrl60;
        }

        public void setArtworkUrl60(String artworkUrl60) {
            this.artworkUrl60 = artworkUrl60;
        }

        public String getArtworkUrl100() {
            return artworkUrl100;
        }

        public void setArtworkUrl100(String artworkUrl100) {
            this.artworkUrl100 = artworkUrl100;
        }

        public double getCollectionPrice() {
            return collectionPrice;
        }

        public void setCollectionPrice(double collectionPrice) {
            this.collectionPrice = collectionPrice;
        }

        public double getTrackPrice() {
            return trackPrice;
        }

        public void setTrackPrice(double trackPrice) {
            this.trackPrice = trackPrice;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getCollectionExplicitness() {
            return collectionExplicitness;
        }

        public void setCollectionExplicitness(String collectionExplicitness) {
            this.collectionExplicitness = collectionExplicitness;
        }

        public String getTrackExplicitness() {
            return trackExplicitness;
        }

        public void setTrackExplicitness(String trackExplicitness) {
            this.trackExplicitness = trackExplicitness;
        }

        public int getDiscCount() {
            return discCount;
        }

        public void setDiscCount(int discCount) {
            this.discCount = discCount;
        }

        public int getDiscNumber() {
            return discNumber;
        }

        public void setDiscNumber(int discNumber) {
            this.discNumber = discNumber;
        }

        public int getTrackCount() {
            return trackCount;
        }

        public void setTrackCount(int trackCount) {
            this.trackCount = trackCount;
        }

        public int getTrackNumber() {
            return trackNumber;
        }

        public void setTrackNumber(int trackNumber) {
            this.trackNumber = trackNumber;
        }

        public int getTrackTimeMillis() {
            return trackTimeMillis;
        }

        public void setTrackTimeMillis(int trackTimeMillis) {
            this.trackTimeMillis = trackTimeMillis;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getPrimaryGenreName() {
            return primaryGenreName;
        }

        public void setPrimaryGenreName(String primaryGenreName) {
            this.primaryGenreName = primaryGenreName;
        }

        public boolean isIsStreamable() {
            return isStreamable;
        }

        public void setIsStreamable(boolean isStreamable) {
            this.isStreamable = isStreamable;
        }

        public String getCollectionArtistName() {
            return collectionArtistName;
        }

        public void setCollectionArtistName(String collectionArtistName) {
            this.collectionArtistName = collectionArtistName;
        }

        public double getTrackRentalPrice() {
            return trackRentalPrice;
        }

        public void setTrackRentalPrice(double trackRentalPrice) {
            this.trackRentalPrice = trackRentalPrice;
        }

        public double getCollectionHdPrice() {
            return collectionHdPrice;
        }

        public void setCollectionHdPrice(double collectionHdPrice) {
            this.collectionHdPrice = collectionHdPrice;
        }

        public double getTrackHdPrice() {
            return trackHdPrice;
        }

        public void setTrackHdPrice(double trackHdPrice) {
            this.trackHdPrice = trackHdPrice;
        }

        public double getTrackHdRentalPrice() {
            return trackHdRentalPrice;
        }

        public void setTrackHdRentalPrice(double trackHdRentalPrice) {
            this.trackHdRentalPrice = trackHdRentalPrice;
        }

        public String getContentAdvisoryRating() {
            return contentAdvisoryRating;
        }

        public void setContentAdvisoryRating(String contentAdvisoryRating) {
            this.contentAdvisoryRating = contentAdvisoryRating;
        }

        public boolean isHasITunesExtras() {
            return hasITunesExtras;
        }

        public void setHasITunesExtras(boolean hasITunesExtras) {
            this.hasITunesExtras = hasITunesExtras;
        }
    }
}
