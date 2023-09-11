import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetuseremailComponent } from './getuseremail.component';

describe('GetuseremailComponent', () => {
  let component: GetuseremailComponent;
  let fixture: ComponentFixture<GetuseremailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetuseremailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetuseremailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
