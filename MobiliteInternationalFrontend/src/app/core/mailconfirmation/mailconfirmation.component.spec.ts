import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MailconfirmationComponent } from './mailconfirmation.component';

describe('MailconfirmationComponent', () => {
  let component: MailconfirmationComponent;
  let fixture: ComponentFixture<MailconfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MailconfirmationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MailconfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
