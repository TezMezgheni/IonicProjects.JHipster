import { IPhoto } from 'app/entities/photo/photo.model';

export interface ITag {
  id?: number;
  name?: string;
  photos?: IPhoto[] | null;
}

export class Tag implements ITag {
  constructor(public id?: number, public name?: string, public photos?: IPhoto[] | null) {}
}

export function getTagIdentifier(tag: ITag): number | undefined {
  return tag.id;
}
