import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CandidatureService } from '../candidature.service';

@Component({
  selector: 'app-listcandidature',
  templateUrl: './listcandidature.component.html',
  styleUrls: ['./listcandidature.component.css']
})
export class ListcandidatureComponent implements OnInit {
  candidatures: any[];
  PDF:any;
  constructor(
    private service : CandidatureService,
    private router:Router
  ) {}
  public imageURL: string;
  file: File | null = null;
  message: any;
 
  ngOnInit(): void {
    
    this.GetCandidatures();
    };

  
  GetCandidatures(){
     this.service.ShowAllCandidature().subscribe(data => {
      this.candidatures = data as any[];

    });
    

  }
 /* GeneratePDF(idCandidature: number){
    this.service.GeneratePDF(idCandidature).subscribe(response => {

        console.log("PDF Downloaded successfully.");

   });

}*/
generatePdf(idCandidature: number) {
  this.service.generatePdf(idCandidature).subscribe((pdfBlob: Blob) => {
    const url = window.URL.createObjectURL(pdfBlob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'candidature.pdf';
    link.click();
    window.URL.revokeObjectURL(url);
  }, error => {
    console.error('Error downloading PDF:', error);
  });
}
  deleteoffers(id: number) {
    this.service.Delete(id).subscribe(
      response => {
        console.log("Offer deleted successfully.");
        this.GetCandidatures();

      },
      error => {
        console.log("Error while deleting Offer:", error);
      }
    );
 
}
  }