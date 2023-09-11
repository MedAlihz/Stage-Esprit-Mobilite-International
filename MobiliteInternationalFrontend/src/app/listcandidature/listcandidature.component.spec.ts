import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListcandidatureComponent } from './listcandidature.component';

describe('ListcandidatureComponent', () => {
  let component: ListcandidatureComponent;
  let fixture: ComponentFixture<ListcandidatureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListcandidatureComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListcandidatureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
