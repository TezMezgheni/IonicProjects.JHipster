jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { PhotoService } from '../service/photo.service';
import { IPhoto, Photo } from '../photo.model';
import { IAlbum } from 'app/entities/album/album.model';
import { AlbumService } from 'app/entities/album/service/album.service';
import { ITag } from 'app/entities/tag/tag.model';
import { TagService } from 'app/entities/tag/service/tag.service';

import { PhotoUpdateComponent } from './photo-update.component';

describe('Component Tests', () => {
  describe('Photo Management Update Component', () => {
    let comp: PhotoUpdateComponent;
    let fixture: ComponentFixture<PhotoUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let photoService: PhotoService;
    let albumService: AlbumService;
    let tagService: TagService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [PhotoUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(PhotoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PhotoUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      photoService = TestBed.inject(PhotoService);
      albumService = TestBed.inject(AlbumService);
      tagService = TestBed.inject(TagService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call Album query and add missing value', () => {
        const photo: IPhoto = { id: 456 };
        const album: IAlbum = { id: 63869 };
        photo.album = album;

        const albumCollection: IAlbum[] = [{ id: 79194 }];
        jest.spyOn(albumService, 'query').mockReturnValue(of(new HttpResponse({ body: albumCollection })));
        const additionalAlbums = [album];
        const expectedCollection: IAlbum[] = [...additionalAlbums, ...albumCollection];
        jest.spyOn(albumService, 'addAlbumToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ photo });
        comp.ngOnInit();

        expect(albumService.query).toHaveBeenCalled();
        expect(albumService.addAlbumToCollectionIfMissing).toHaveBeenCalledWith(albumCollection, ...additionalAlbums);
        expect(comp.albumsSharedCollection).toEqual(expectedCollection);
      });

      it('Should call Tag query and add missing value', () => {
        const photo: IPhoto = { id: 456 };
        const tags: ITag[] = [{ id: 49103 }];
        photo.tags = tags;

        const tagCollection: ITag[] = [{ id: 69813 }];
        jest.spyOn(tagService, 'query').mockReturnValue(of(new HttpResponse({ body: tagCollection })));
        const additionalTags = [...tags];
        const expectedCollection: ITag[] = [...additionalTags, ...tagCollection];
        jest.spyOn(tagService, 'addTagToCollectionIfMissing').mockReturnValue(expectedCollection);

        activatedRoute.data = of({ photo });
        comp.ngOnInit();

        expect(tagService.query).toHaveBeenCalled();
        expect(tagService.addTagToCollectionIfMissing).toHaveBeenCalledWith(tagCollection, ...additionalTags);
        expect(comp.tagsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const photo: IPhoto = { id: 456 };
        const album: IAlbum = { id: 38370 };
        photo.album = album;
        const tags: ITag = { id: 37352 };
        photo.tags = [tags];

        activatedRoute.data = of({ photo });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(photo));
        expect(comp.albumsSharedCollection).toContain(album);
        expect(comp.tagsSharedCollection).toContain(tags);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Photo>>();
        const photo = { id: 123 };
        jest.spyOn(photoService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ photo });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: photo }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(photoService.update).toHaveBeenCalledWith(photo);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Photo>>();
        const photo = new Photo();
        jest.spyOn(photoService, 'create').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ photo });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: photo }));
        saveSubject.complete();

        // THEN
        expect(photoService.create).toHaveBeenCalledWith(photo);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Photo>>();
        const photo = { id: 123 };
        jest.spyOn(photoService, 'update').mockReturnValue(saveSubject);
        jest.spyOn(comp, 'previousState');
        activatedRoute.data = of({ photo });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(photoService.update).toHaveBeenCalledWith(photo);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackAlbumById', () => {
        it('Should return tracked Album primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackAlbumById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackTagById', () => {
        it('Should return tracked Tag primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackTagById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });

    describe('Getting selected relationships', () => {
      describe('getSelectedTag', () => {
        it('Should return option if no Tag is selected', () => {
          const option = { id: 123 };
          const result = comp.getSelectedTag(option);
          expect(result === option).toEqual(true);
        });

        it('Should return selected Tag for according option', () => {
          const option = { id: 123 };
          const selected = { id: 123 };
          const selected2 = { id: 456 };
          const result = comp.getSelectedTag(option, [selected2, selected]);
          expect(result === selected).toEqual(true);
          expect(result === selected2).toEqual(false);
          expect(result === option).toEqual(false);
        });

        it('Should return option if this Tag is not selected', () => {
          const option = { id: 123 };
          const selected = { id: 456 };
          const result = comp.getSelectedTag(option, [selected]);
          expect(result === option).toEqual(true);
          expect(result === selected).toEqual(false);
        });
      });
    });
  });
});
