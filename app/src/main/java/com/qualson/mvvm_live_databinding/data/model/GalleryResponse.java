package com.qualson.mvvm_live_databinding.data.model;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by ykim on 2017. 4. 11..
 */

@Accessors(chain = true) @Data public class GalleryResponse {
  private boolean success;
  private int status;
  private List<GalleryImage> data;
}
