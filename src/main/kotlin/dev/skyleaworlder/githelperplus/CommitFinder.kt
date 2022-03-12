package dev.skyleaworlder.githelperplus

import org.eclipse.jgit.lib.ObjectId
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.revwalk.RevWalk

/**
 * walk.markUninterestring is not a suitable solution when
 * getting all the commits merged in from a old commit to a new one,
 * because some commits, unmerged at the point of the old commit,
 * might do a merge job in the process of development from old to new.
 *
 * a commit should abide by the restriction that
 * commit in walk.markStart(newCommit) but not in walk.markStart(oldCommit).
 */
class CommitFinder(private val repository: Repository) {
    fun getCommitsBetweenTwoCommits(newCommitId: String, oldObjectId: String): List<RevCommit> {
        return getCommitsBetweenTwoCommits(
            repository.resolve(newCommitId),
            repository.resolve(oldObjectId)
        )
    }

    fun getCommitsBetweenTwoCommits(newObjectId: ObjectId, oldObjectId: ObjectId): List<RevCommit> {
        return getCommitsBetweenTwoCommits(
            repository.parseCommit(newObjectId),
            repository.parseCommit(oldObjectId)
        )
    }

    fun getCommitsBetweenTwoCommits(newCommit: RevCommit, oldCommit: RevCommit): List<RevCommit> {
        var result: ArrayList<RevCommit> = ArrayList()
        val commitsUnderOldCommit: List<RevCommit> = this.getCommitsUnderCommit(oldCommit)
        var walk = RevWalk(repository)
        walk.markStart(newCommit)
        walk.iterator().forEach { if (!commitsUnderOldCommit.contains(it)) {
                result.add(it)
        } }
        return result
    }

    private fun getCommitsUnderCommit(commit: RevCommit): List<RevCommit> {
        var result: ArrayList<RevCommit> = ArrayList()
        var walk = RevWalk(repository)
        walk.markStart(commit)
        walk.iterator().forEach { result.add(it) }
        return result
    }
}