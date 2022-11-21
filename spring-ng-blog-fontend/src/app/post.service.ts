import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostPayload } from './add-post/post-payload';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  url = 'http://localhost:8080/api/posts/';

  constructor(private httpClient: HttpClient) { }

  addPost(postPayload: PostPayload) {
    return this.httpClient.post(this.url, postPayload);
  }
}
