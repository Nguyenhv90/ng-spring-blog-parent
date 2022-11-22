import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostPayload } from '../add-post/post-payload';
import { PostService } from '../post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  permaLink!: Number;
  post!: PostPayload;
  constructor(private router: ActivatedRoute, private postService: PostService) { }

  ngOnInit(): void {
    this.router.params.subscribe(params => {
      this.permaLink = params['id']
    });
    this.postService.getPost(this.permaLink).subscribe((data:PostPayload) => {
      this.post = data;
    }, (error : any) => {
      console.log("Fail response");
      
    })
  }

}
