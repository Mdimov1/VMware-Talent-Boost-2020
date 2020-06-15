import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder } from '@angular/forms';
import { Charity } from 'src/app/models/charity';
import { CharityService } from 'src/app/service/charity.service';
import { Router } from '@angular/router';
import { ImageService } from 'src/app/service/image/image.service';

@Component({
  selector: 'app-create-charity',
  templateUrl: './create-charity.component.html',
  styleUrls: ['./create-charity.component.scss']
})

export class CreateCharityComponent implements OnInit {
  imageSrc: string;
  imageFile: File;

  createCharityForm = this.fb.group({
    name: ['', Validators.required],
    description: ['', Validators.required],
    thumbnail_name: ['', Validators.required],
    donation_required: ['', Validators.compose([Validators.required, Validators.min(0)])],
    participants_required: [''],
    author_id: ''
  });
  constructor(private fb: FormBuilder,
    private router: Router,
     private charityService: CharityService,
     private imageService: ImageService) { }

  ngOnInit(): void {
    // Set author id to createCharityForm
    const author_id = Number(localStorage.getItem('user_id'));
    this.createCharityForm.controls.author_id.setValue(author_id);
  }

  selectFile(event){
    const reader = new FileReader();
    
    if(event.target.files && event.target.files.length) {
      // Set selected file to imageFile
      this.imageFile = event.target.files[0];
      // Read file(image) name
      let fileName = this.imageFile.name;
      
      // Set file name to thumbnail name in createCharityForm
      this.createCharityForm.patchValue({
        thumbnail_name: fileName
      });
      
      const [file] = event.target.files;
      reader.readAsDataURL(file);
    
      // Set current image source to show the image
      reader.onload = () => {
        this.imageSrc = reader.result as string;
        console.log(this.imageSrc)
      };
    }
  }

  onSubmit(){
    if (!this.createCharityForm.invalid) {
      // Config image data for the post request
      const imageData = new FormData();
      imageData.append('imageFile', this.imageFile, this.imageFile.name);

      console.log(this.createCharityForm.value);

      // Call method to add charity
      this.charityService.addCharity(this.createCharityForm.value);  
      
      // Call method to upload charity image
      this.imageService.uploadImage(imageData);  

      this.router.navigate([('/home')]);
    }
  }
  
}
