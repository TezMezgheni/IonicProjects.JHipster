import * as dayjs from 'dayjs';
import { IAlbum } from 'app/entities/album/album.model';
import { ITag } from 'app/entities/tag/tag.model';

export interface IPhoto {
  id?: number;
  title?: string;
  description?: string | null;
  imageContentType?: string;
  image?: string;
  taken?: dayjs.Dayjs | null;
  album?: IAlbum | null;
  tags?: ITag[] | null;
}

export class Photo implements IPhoto {
  constructor(
    public id?: number,
    public title?: string,
    public description?: string | null,
    public imageContentType?: string,
    public image?: string,
    public taken?: dayjs.Dayjs | null,
    public album?: IAlbum | null,
    public tags?: ITag[] | null
  ) {}
}

export function getPhotoIdentifier(photo: IPhoto): number | undefined {
  return photo.id;
}
