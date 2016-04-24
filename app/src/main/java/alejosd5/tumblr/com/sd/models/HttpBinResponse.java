package alejosd5.tumblr.com.sd.models;

import java.util.Map;

/**
 * Created by alejosd on 23/04/2016.
 */
public class HttpBinResponse {

    // the request url
    String url;

    // the requester ip
    String origin;

    // all headers that have been sent
    Map headers;

    // url arguments
    Map args;

    // post form parameters
    Map form;

    // post body json
    Map json;
}
